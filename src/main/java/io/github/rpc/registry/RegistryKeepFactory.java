package io.github.rpc.registry;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSON;

import io.github.pikaq.common.util.NameThreadFactoryImpl;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RegistryKeepFactory {

	private List<RegistryServiceWrapper> holder = new CopyOnWriteArrayList<>();
	private ReadWriteLock lock = new ReentrantReadWriteLock();

	private final Path fullStorePath;
	private final String fileName = "/rpc_service_registry.json";

	public RegistryKeepFactory() {
		fullStorePath = Paths.get("", fileName);
	}

	/**
	 * 初始化，读取文件并加载到内存对象中
	 */
	public void Initialization() {
		String jsonString = this.read();
		if (StringUtils.isNotBlank(jsonString)) {

			this.holder = JSON.parseArray(jsonString, RegistryServiceWrapper.class);
			log.info("加载服务注册数据成功：\n {}", serialPrettyFormat());
		}
		this.printPeriodically();
	}

	/**
	 * 获取当前可用的RegistryServiceWrapper信息，注意：并不一定是实时可用的
	 */
	public List<RegistryServiceWrapper> currentRegistryServiceWrapper() {
		try {
			lock.readLock().lock();
			return this.holder;
		} finally {
			lock.readLock().unlock();
		}
	}

	/**
	 * 保存服务信息到内存并持久化，先删除原来所有再添加
	 */
	public void save(RegistryServiceWrapper serviceWrapper) {
		try {
			lock.writeLock().lock();
			RegistryServiceWrapper prev = new RegistryServiceWrapper();
			prev.setServiceName(serviceWrapper.getServiceName());
			holder.remove(prev);
			holder.add(serviceWrapper);
		} finally {
			lock.writeLock().unlock();
		}
		this.persist();
	}

	/**
	 * 通过重写equal完成对象移除
	 */
	public void remove(String serviceName) {
		RegistryServiceWrapper other = new RegistryServiceWrapper();
		other.setServiceName(serviceName);
		try {
			lock.writeLock().lock();
			holder.remove(other);
		} finally {
			lock.writeLock().unlock();
		}
		this.persist();
	}

	/**
	 * 清除内存中，不清空文件
	 */
	public void clearAll() {
		try {
			lock.writeLock().lock();
			holder.clear();
		} finally {
			lock.writeLock().unlock();
		}
		this.persist();
	}
	
	/**
	 * 持久化<br>
	 * 使用读锁，与其他读锁操作互不阻塞
	 */
	public void persist() {
		try {
			lock.readLock().lock();
			Files.write(this.fullStorePath, serialPrettyFormat().getBytes());
			log.info("{} OK.", fullStorePath);
		} catch (IOException e) {
			log.error("写入{}文件失败：", fullStorePath, e);
		} finally {
			lock.readLock().unlock();
		}
	}

	/**
	 * 读取存储文件，当文件不存在或读取异常时返回空字符串
	 */
	public String read() {
		String content = "";
		lock.readLock().lock();
		try {
			boolean exists = Files.exists(this.fullStorePath);
			if (!exists) {
				return content;
			}
			content = new String(Files.readAllBytes(this.fullStorePath));
		} catch (IOException e) {
			log.warn("读取{}文件失败： ", fullStorePath, e);
		} finally {
			lock.readLock().unlock();
		}
		return content;
	}

	/**
	 * 人类可读的JSON形式
	 */
	private String serialPrettyFormat() {
		return JSON.toJSONString(holder, true);
	}

	private void printPeriodically() {
		ScheduledExecutorService scheduledExecutorService = Executors
				.newSingleThreadScheduledExecutor((new NameThreadFactoryImpl("print_registryKeepFactory", true)));
		scheduledExecutorService.scheduleAtFixedRate(() -> log.info("\n" + serialPrettyFormat()), 1, 30,
				TimeUnit.MINUTES);
	}

}
