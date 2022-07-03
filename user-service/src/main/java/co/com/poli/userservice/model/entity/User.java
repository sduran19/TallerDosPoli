package co.com.poli.userservice.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotEmpty(message = "El nombre no puede ser vacio")
    private String name;
    @NotEmpty(message = "El apellido no puede ser vacio")
    @Column(name = "lastname")
    private String lastName;
}
