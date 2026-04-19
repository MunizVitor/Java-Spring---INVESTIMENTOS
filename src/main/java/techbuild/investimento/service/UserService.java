package techbuild.investimento.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import techbuild.investimento.DTO.CreateUserDTO;
import techbuild.investimento.DTO.UpdateUserDTO;
import techbuild.investimento.entity.User;
import techbuild.investimento.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public UUID createUser(CreateUserDTO createUserDTO){
        User user = new User(createUserDTO);
        var userSaved = repository.save(user);
        return userSaved.getUserId();
    }

    public Optional<User> getUserById(String userId){
        return repository.findByUserIdAndIsAtivoTrue(UUID.fromString(userId));//tranformando String em UUID
    }

    public List<User> listUsers(){
        return repository.findByIsAtivo(true);
    }

    public boolean uptadeUser(String userId, UpdateUserDTO updateUserDTO){
        Optional<User> optionalUser = repository.findByUserIdAndIsAtivoTrue(UUID.fromString(userId));

        if(optionalUser.isPresent()){
            User user = optionalUser.get();

            if(updateUserDTO.username() != null) user.setUsername(updateUserDTO.username());
            if(updateUserDTO.password() != null) user.setPassword(updateUserDTO.password());

            repository.save(user);
            return true;
        }

        return false;
    }

    public void deletar(String userId) {
        Optional<User> optionalUser = repository.findByUserIdAndIsAtivoTrue(UUID.fromString(userId));

        if(optionalUser.isPresent()){
            User user = optionalUser.get(); // pega o User de dentro do Optional
            user.inativar(); // metodo que muda o status
            repository.save(user); // salva a alteração
        }
    }
}
