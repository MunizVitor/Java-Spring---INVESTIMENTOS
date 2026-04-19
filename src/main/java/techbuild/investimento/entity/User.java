package techbuild.investimento.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import techbuild.investimento.DTO.CreateUserDTO;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "tb_users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID userId;

    @Column(name = "username")
    private String username;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "is_Ativo")
    private boolean isAtivo = true;

    @CreationTimestamp
    private Instant createdTimetamp;//marca a data da criacao do usuario

    @UpdateTimestamp
    private Instant updateTimetamp;//marca a data de uptade do usuario

    public User(CreateUserDTO createUserDTO) {
        this.username = createUserDTO.username();
        this.email = createUserDTO.email();
        this.password = createUserDTO.password();
        this.createdTimetamp = Instant.now();
        this.updateTimetamp = Instant.now();
    }

    public void Atualizar (CreateUserDTO createUserDTO){
        this.username = createUserDTO.username();
        this.email = createUserDTO.email();
        this.password = createUserDTO.password();
        this.updateTimetamp = Instant.now();
    }

    public void inativar (){
        this.isAtivo = false;
    }

    public void ativar (){
        this.isAtivo = true;
    }
}
