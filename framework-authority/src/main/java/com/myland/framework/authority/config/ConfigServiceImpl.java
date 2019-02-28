package com.myland.framework.authority.config;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.myland.framework.authority.dao.ConfigDao;
import com.myland.framework.authority.po.Config;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 系统配置项Service实现类
 *
 * @author SunYanQing
 * @version 1.0
 * @date 2018-11-30 16:29:35
 */
@Service("configService")
public class ConfigServiceImpl implements ConfigService {
	@Resource
	private ConfigDao configDao;

	@Override
	public Config getObjById(Long id) {
		return configDao.selectByPrimaryKey(id);
	}

	@Override
	public PageInfo<Config> getList4Page(Map<String, Object> map) {
		String pageNum = (String) map.get("pageNum");
		String pageSize = (String) map.get("pageSize");
		PageHelper.startPage(Integer.parseInt(pageNum), Integer.parseInt(pageSize));
		return new PageInfo<>(configDao.selectList(map));
	}

	@Override
	public List<Config> getAll() {
		return configDao.selectAll();
	}

	@Override
	public boolean checkKeyUnique(String key) {
		return configDao.selectCountByKey(key) == 0;
	}

	@Override
	public void enable(List<Long> ids) {
		Map<String, Object> map = new HashMap<>(2);
		map.put("ids", ids);
		map.put("flag", "1");
		configDao.updateEnabled(map);
	}

	@Override
	public void disable(List<Long> ids) {
		Map<String, Object> map = new HashMap<>(2);
		map.put("ids", ids);
		map.put("flag", "0");
		configDao.updateEnabled(map);
	}

	@Override
	public void save(Config config) {
            configDao.insert(config);
	}

	@Override
	public void update(Config config) {
            configDao.updateByPrimaryKeySelective(config);
	}

	@Override
	public void delete(Long id) {
            configDao.deleteByPrimaryKey(id);
	}

}