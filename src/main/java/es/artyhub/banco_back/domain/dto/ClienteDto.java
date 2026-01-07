package es.artyhub.banco_back.domain.dto;

public class ClienteDto {
    private Long id;
    private String login;
    private String password;
    private String name;
    private String lastName1;
    private String lastName2;
    private String dni;
    private String api_token;

    public ClienteDto() {
    }

    public ClienteDto(Long id, String login, String password, String name, String lastName1, String lastName2,
            String dni, String api_token) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.name = name;
        this.lastName1 = lastName1;
        this.lastName2 = lastName2;
        this.dni = dni;
        this.api_token = api_token;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName1() {
        return lastName1;
    }

    public void setLastName1(String lastName1) {
        this.lastName1 = lastName1;
    }

    public String getLastName2() {
        return lastName2;
    }

    public void setLastName2(String lastName2) {
        this.lastName2 = lastName2;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getApi_token() {
        return api_token;
    }

    public void setApi_token(String api_token) {
        this.api_token = api_token;
    }
}
