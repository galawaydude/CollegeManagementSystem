enum credentials{
     url("jdbc:postgresql://localhost:5432/JavaProject"),
     password("%Fortress123&"),
     username("postgres");
     public final String value;

    private credentials(String value) {
        this.value = value;
    }

    public String getValue(){
        return this.value;
    }
}