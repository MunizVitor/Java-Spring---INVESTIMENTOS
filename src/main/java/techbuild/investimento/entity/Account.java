package techbuild.investimento.entity;

import jakarta.persistence.*;
import jakarta.websocket.server.ServerEndpoint;
import lombok.Getter;
import lombok.Setter;

import javax.naming.spi.NamingManager;
import java.security.PrivilegedAction;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tb_account")
@Getter
@Setter
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID accountId;

    @OneToOne(mappedBy = "account")
    @PrimaryKeyJoinColumn
    private BilingAddress bilingAddress;

    @ManyToOne//Uma conta pode ter apenas um usuario
    @JoinColumn(name = "user_id")//cria na tabela uma forenKey falando que esta chave refenrecia a outra tabela
    private User user;

    @Column(name = "deescription")
    private String description;

    @OneToMany(mappedBy = "account")
    private List<AccountStock> accountStocks;
}
