package com.acton.db;

import java.util.ArrayList;
import java.util.List;

import com.acton.domain.Permission;
import com.acton.domain.Role;
import com.acton.domain.User;



public class DAO {
	static Data data = new Data();

	public static User getUserById(String userid) {
		return data.getUserMap().get(userid);
	}

	public static List<Permission> getPermissionsById(String userid) {
		List<Permission> permList = new ArrayList<Permission>();
		User user = getUserById(userid);
		List<Role> roleList = user.getRoleList();
		for (Role role : roleList)
			permList.addAll(role.getPermList());
		return permList;
	}

	public static List<Permission> getPermissionsByUser(String userid, User user) {
		List<Permission> permList = new ArrayList<Permission>();
		List<Role> roleList = user.getRoleList();
		for (Role role : roleList)
			permList.addAll(role.getPermList());
		return permList;
	}

	public static Boolean isUserEntitled(String userid, String permid) {

		List<Permission> permList = getPermissionsById(userid);
		return permList.contains(data.getPermMap().get(permid));
	}

	public static void modifyPermissions(String roleid,
			List<Permission> permissions) {
		data.getRoleMap().get(roleid).setPermList(permissions);
	}

	public static void deletePermissionById(String permid) {
		data.deletePermissionById(permid);
		
	}
}
