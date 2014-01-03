package com.acton.service;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.acton.db.DAO;
import com.acton.domain.Permission;
import com.acton.domain.User;
@Path("rest")
public class ActOnRestService {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	//@Path("/checkpermission?user={userid}&permission={permid}")
	@Path("/checkpermission")
	public Boolean isUserEntitled(@QueryParam("userid") String userid,
			@QueryParam("permid") String permid) {
		Boolean isEntitled = DAO.isUserEntitled(userid,permid);
        return isEntitled;		
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/user/{userid}")
	public Response getUserPermissions(@PathParam("userid") String userid) {
		User user = DAO.getUserById(userid);
		if(user==null)
			return Response.status(Response.Status.NOT_FOUND).entity("User Not Found").type("text/plain").build();
		
		List<Permission> list = DAO.getPermissionsById(userid);
		GenericEntity<List<Permission>> listEntity = new GenericEntity<List<Permission>>(
				list) {
		};
		return Response.status(Response.Status.OK).entity(listEntity).build();
	}

	

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/role/{roleid}")
	public Response modifyPermissions(@PathParam("roleid") String roleid,List<Permission> permissions)
	{
		DAO.modifyPermissions(roleid,permissions);
		return Response.status(Response.Status.OK).build();
	}
	
	@DELETE
	@Path("/permissions/{permid}")
	public Response deletePermission(@PathParam("permid") String permid)
	{
		DAO.deletePermissionById(permid);
		return Response.noContent().build();
	}
	


}
