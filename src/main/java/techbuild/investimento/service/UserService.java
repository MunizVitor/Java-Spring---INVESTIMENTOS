package techbuild.investimento.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import techbuild.investimento.DTO.*;
import techbuild.investimento.entity.Account;
import techbuild.investimento.entity.BillingAddress;
import techbuild.investimento.entity.User;
import techbuild.investimento.repository.AccountRepository;
import techbuild.investimento.repository.BilingAddressRepository;
import techbuild.investimento.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static java.util.Objects.isNull;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private BilingAddressRepository bilingAddressRepository;

    public UUID createUser(CreateUserDTO createUserDTO){
        User user = new User(createUserDTO);
        var userSaved = repository.save(user);
        return userSaved.getUserId();
    }

    public UserResponseDTO getUserById(String userId) {

        var user = repository.findByUserIdAndIsAtivoTrue(UUID.fromString(userId))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        var accounts = user.getAccountList().stream()
                .map(account -> new AccountResponseDTO(
                        account.getAccountId(),
                        account.getDescription(),
                        account.getBillingAddress() != null
                                ? new BillingAddressDTO(
                                account.getBillingAddress().getStreet(),
                                account.getBillingAddress().getNumber()
                        )
                                : null
                ))
                .toList();

        return new UserResponseDTO(
                user.getUserId(),
                user.getUsername(),
                user.getEmail(),
                accounts
        );
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

    public void createAccount(String userId, CreatedAccountDTO createAccountDto) {

        var user = repository.findByUserIdAndIsAtivoTrue(UUID.fromString(userId))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario não existe"));

        if (isNull(user.getAccountList())) {
            user.setAccountList(new ArrayList<>());
        }

        // ❌ NÃO setar ID manual
        var account = new Account();
        account.setUser(user);
        account.setDescription(createAccountDto.description());
        account.setAccountStocks(new ArrayList<>());

        var accountCreated = accountRepository.save(account);

        var billingAddress = new BillingAddress();
        billingAddress.setAccount(accountCreated); // MapsId cuida do ID
        billingAddress.setStreet(createAccountDto.street());
        billingAddress.setNumber(createAccountDto.number());

        bilingAddressRepository.save(billingAddress);
    }

//    public List<AccountResponseDTO> listAccounts(String userId) {
//        var user = repository.findById(UUID.fromString(userId))
//                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
//
//        return user.getAccountList()
//                .stream()
//                .map(ac ->
//                        new AccountResponseDTO(ac.getAccountId().toString(), ac.getDescription()))
//                .toList();
//    }
}
