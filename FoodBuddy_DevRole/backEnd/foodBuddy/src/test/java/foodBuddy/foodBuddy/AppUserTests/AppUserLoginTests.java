package foodBuddy.foodBuddy.AppUserTests;

import foodBuddy.foodBuddy.appuser.AppUser;
import foodBuddy.foodBuddy.appuser.AppUserLogin;
import foodBuddy.foodBuddy.appuser.UserRepository;
import foodBuddy.foodBuddy.constants.AppConstants;
import foodBuddy.foodBuddy.groupManagement.ViewGroupUsers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class AppUserLoginTests {

    private AppUserLogin appUserLogin;
    @Mock
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        appUserLogin = new AppUserLogin();
    }

    @Test
    void findByEmailTest(){
        String emailId="user@email.com";
        Optional<AppUser> result = appUserLogin.userRepository.findByEmail("email");
        assertEquals(Optional.empty(),result);
    }

    @Test
    void findPasswordByEmailTest(){
        String emailId="user@email.com";
        String password = appUserLogin.userRepository.findPasswordByEmail(emailId);
        assertEquals(null,password);
    }

    @Test
    void findUsersByGroupCodeTest(){
        String groupCode="1234";
        List<ViewGroupUsers> result = appUserLogin.userRepository.findUsersByGroupCode(groupCode);
        assertEquals(null,result);
    }

    @Test
    void findGroupByEmailTest(){
        String emailId="user@email.com";
        String result = appUserLogin.userRepository.findGroupByEmail(emailId);
        assertEquals(null,result);
    }

    @Test
    void findUsernamesTest(){
        String groupCode="1234";
        List<String> result = appUserLogin.userRepository.findUsernames(groupCode);
        assertEquals(null,result);
    }

    @Test
    void getOneTest(){
        Long param = (Long.parseLong(AppConstants.DEFAULT_LONG_VALUE.getValue().toString()));
        AppUser result = appUserLogin.userRepository.getOne(param);
        assertEquals(null,result);

    }

    @Test
    void getByIdTest(){
        Long param = (Long.parseLong(AppConstants.DEFAULT_LONG_VALUE.getValue().toString()));
        AppUser user = appUserLogin.userRepository.getById(param);
        assertEquals(null,user);
    }

    @Test
    void getReferenceByIdTest(){
        Long param = (Long.parseLong(AppConstants.DEFAULT_LONG_VALUE.getValue().toString()));
        AppUser result = appUserLogin.userRepository.getReferenceById(param);
        assertEquals(null,result);
    }

    @Test
    void findAll(){
        List<AppUser> users = appUserLogin.userRepository.findAll();
        assertEquals(null,users);
    }

    @Test
    void findAllByIdTest(){
        Iterable<Long> longs = null;
        List<AppUser> result = appUserLogin.userRepository.findAllById(longs);
        assertEquals(null,result);
    }

    @Test
    void findByIdTest(){
        Long param = (Long.parseLong(AppConstants.DEFAULT_LONG_VALUE.getValue().toString()));
        Optional<AppUser> user = appUserLogin.userRepository.findById(param);
        assertEquals(Optional.empty(),user);
    }

    @Test
    void existsByIdTest(){
        Long id = (Long.parseLong(AppConstants.DEFAULT_LONG_VALUE.getValue().toString()));
        boolean isExisting = appUserLogin.userRepository.existsById(id);
        assertFalse(isExisting);
    }

    @Test
    void countTest(){
        long countParam = appUserLogin.userRepository.count();
        assertEquals(0,countParam);

    }

    @Test
    void findAllSortTest(){
        Sort sort = null;
        List<AppUser> result = appUserLogin.userRepository.findAll(sort);
        assertEquals(null,result);
    }

    @Test
    void findAllPageableTest(){
        Pageable pageable = null;
        Page<AppUser> result = appUserLogin.userRepository.findAll(pageable);
        assertEquals(result,null);
    }

    @Test
    void loadUserByUsernameTest(){
        String userName="user@email.com";
        UserDetails user;
        assertThrows(UsernameNotFoundException.class,() -> appUserLogin.loadUserByUsername(userName));
    }

}
