package residentEvilApp.model.enums;

/**
 * Created by George-Lenovo on 20/03/2018.
 */
public enum Role {
    ADMIN, /*everything*/
    USER, /*[/viruses/show], [/], [/logout].*/
    MODERATOR; /*[/viruses/add], [/viruses/edit], [/viruses/delete].*/
}
