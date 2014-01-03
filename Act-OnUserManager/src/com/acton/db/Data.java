package com.acton.db;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;

import com.acton.domain.Permission;
import com.acton.domain.Role;
import com.acton.domain.User;

@XmlRootElement
class Data {
	public Data() {
		load();
	}

	private Map<String, User> userMap = new HashMap<String, User>();
	private Map<String, Role> roleMap = new HashMap<String, Role>();;
	private Map<String, Permission> permMap = new HashMap<String, Permission>();;

	public Map<String, User> getUserMap() {
		return userMap;
	}

	public void setUserMap(Map<String, User> userMap) {
		this.userMap = userMap;
	}

	public Map<String, Role> getRoleMap() {
		return roleMap;
	}

	public void setRoleMap(Map<String, Role> roleMap) {
		this.roleMap = roleMap;
	}

	public Map<String, Permission> getPermMap() {
		return permMap;
	}

	public void setPermMap(Map<String, Permission> permMap) {
		this.permMap = permMap;
	}

	public void deletePermissionById(String permid) {
		Permission perm = permMap.get(permid);
		if (perm != null) {
			permMap.remove(permid);

			List<String> affectedRolesList = new ArrayList<String>();
			for (String roleid : roleMap.keySet()) {
				// TODO overriding equals & hashcode of Permission
				// data.getRoleMap().get(roleid).getPermList().remove(perm);
				List<Permission> listByRole = roleMap.get(roleid).getPermList();

				ListIterator<Permission> li = listByRole.listIterator();
				while (li.hasNext()) {
					Permission p = li.next();
					if (p.getId().equals(permid)) {
						li.remove();
						affectedRolesList.add(roleid);
					}
				}

			}

			for (String userid : userMap.keySet()) {

				List<Role> rolesByUserId = userMap.get(userid).getRoleList();
				ListIterator<Role> lir = rolesByUserId.listIterator();
				while (lir.hasNext()) {
					Role role = lir.next();
					if (affectedRolesList.contains(role.getId())) {
						role.setPermList(roleMap.get(role.getId())
								.getPermList());
					}
				}

			}
		}
	}

	void load() {

		Permission perm1 = new Permission("perm1", "Can Check Balance");
		Permission perm5 = new Permission("perm5", "Can Deposit");
		Permission perm6 = new Permission("perm6", "Can Tranfer");
		Permission perm7 = new Permission("perm7", "Can Withdraw");

		Role role1 = new Role("role1", new ArrayList<Permission>());
		role1.getPermList().add(perm1);
		role1.getPermList().add(perm5);

		Role role3 = new Role("role3", new ArrayList<Permission>());
		role3.getPermList().add(perm6);
		role3.getPermList().add(perm7);

		User user1 = new User("user1", new ArrayList<Role>());
		user1.getRoleList().add(role1);
		user1.getRoleList().add(role3);

		userMap.put("user1", user1);

		roleMap.put("role1", role1);
		roleMap.put("role3", role3);
		
		permMap.put("perm1", perm1);
		permMap.put("perm5", perm5);
		permMap.put("perm6", perm6);
		permMap.put("perm7", perm7);

	}
}
