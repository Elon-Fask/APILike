package com.apilike.services;

import java.awt.image.BufferedImage;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.multipart.MultipartFile;

import com.apilike.entity.enums.Profile;
import com.apilike.entity.user.User;
import com.apilike.repositories.UserRepository;
import com.apilike.security.UserSS;
import com.apilike.services.exception.AuthorizationException;
import com.apilike.services.exception.ObjectNotFoundException;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repo;
	
	@Autowired
	private BCryptPasswordEncoder passcrypt;
	
	
    public User find(Integer id) {

        UserSS user = UserService.authenticated();
        if (user == null || !user.hasRole(Profile.ADMIN) && !id.equals(user.getId())) {
            throw new AuthorizationException("Access denied");
        }

        Optional<User> obj = repo.findById(id);
        return obj.orElseThrow(() ->
                new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));

        @Transactional// garantia de que será salvo endereço e cliente na mesma transação no banco de dados.
        public User insert(User obj) {
            obj.setId(null);
            obj = repo.save(obj);
            enderecoRepository.saveAll(obj.getEnderecos());
            return obj; // Faz parte do metodo para inserir um novo cliente junto com endereço

        }
        
        
        
        

    }




	
	
	
	

}
