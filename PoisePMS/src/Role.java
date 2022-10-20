public class Role {

    private int role_id;
    private String role_name;

    public Role(int role_id, String role_name){
        this.role_id = role_id;
        this.role_name = role_name;
    }

    public int getRoleID(){
        return role_id;
    }
    public String getRoleName() {
        return role_name;
    }
    public void setRoleID(int roleID){
        this.role_id = roleID;
    }
    public void setRoleName(String roleName){
        this.role_name = roleName;
    }
}
