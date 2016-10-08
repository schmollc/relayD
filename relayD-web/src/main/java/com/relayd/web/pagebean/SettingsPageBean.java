package com.relayd.web.pagebean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import com.relayd.web.theme.Theme;
import com.relayd.web.theme.ThemeService;

/**
 * @author schmollc (Christian@relayd.de)
 * @since 08.10.2016
 *
 */
@ManagedBean
@SessionScoped
public class SettingsPageBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private List<Theme> themes;

	@ManagedProperty("#{themeService}")
	ThemeService service;

	@PostConstruct
	public void init() {
		themes = service.getThemes();
	}

	public List<Theme> getThemes() {
		return themes;
	}

	public void setService(ThemeService aService) {
		service = aService;
	}

	public String getVersion() {
		return "1.0 - Codename Augustiner";
	}
}