package org.zj.oasys.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 * @author Administrator
 *
 */
@Entity
@Table(name="tb_privilege")
public class Privilege implements Serializable{
	
	private Long id;
	private String name;
	private String code;
	private String url;
	private String icon;
	private Integer status;   // 0 ： 启用   1：未启用
	private Integer type;     //0：模块  1：功能  2：操作
	private Integer order;
	private Privilege parent;
	private Set<Privilege> childs = new HashSet<>();
	
	private Set<Role> roles = new HashSet<>();
	
	
	public Privilege() {
	}
	
	
	
	
	
	public Privilege(String name, String code, String url, String icon, Integer status, Integer type, Integer order,
			Privilege parent) {
		
		this.name = name;
		this.code = code;
		this.url = url;
		this.icon = icon;
		this.status = status;
		this.type = type;
		this.order = order;
		this.parent = parent;
		
	}





	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name="name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(name="code")
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	@Column(name="url")
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	@Column(name="icon")
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	@Column(name="status")
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	@Column(name="type")
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	@Column(name="order_")
	public Integer getOrder() {
		return order;
	}
	public void setOrder(Integer order) {
		this.order = order;
	}
	
	@ManyToOne
	@JoinColumn(name="parentId")
	public Privilege getParent() {
		return parent;
	}
	public void setParent(Privilege parent) {
		this.parent = parent;
	}
	
	@OneToMany(mappedBy="parent")
	@OrderBy(value="order ASC")
	public Set<Privilege> getChilds() {
		return childs;
	}
	public void setChilds(Set<Privilege> childs) {
		this.childs = childs;
	}
	
	@ManyToMany(mappedBy="privileges")
	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
}
