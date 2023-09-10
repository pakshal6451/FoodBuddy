package foodBuddy.foodBuddy.appuser;

import foodBuddy.foodBuddy.groupManagement.ViewGroupUsers;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;


public class AppUserLogin implements UserDetailsService {
    public final UserRepository  userRepository = new UserRepository() {
        @Override
        public Optional<AppUser> findByEmail(String email) {
            return Optional.empty();
        }

        @Override
        public String findPasswordByEmail(String email) {
            return null;
        }

        @Override
        public void UpdateGroupName(String groupCode, String groupName, String userName) {

        }


        @Override
        public List<ViewGroupUsers> findUsersByGroupCode(String groupCode){
            return null;
        }

        @Override
        public String findGroupByEmail(String email){
            return null;
        }

        @Override
        public List<String> findUsernames(String groupCode) {
            return null;
        }

        @Override
        public void flush() {

        }

        @Override
        public <S extends AppUser> S saveAndFlush(S entity) {
            return null;
        }

        @Override
        public <S extends AppUser> List<S> saveAllAndFlush(Iterable<S> entities) {
            return null;
        }

        @Override
        public void deleteAllInBatch(Iterable<AppUser> entities) {

        }

        @Override
        public void deleteAllByIdInBatch(Iterable<Long> longs) {

        }

        @Override
        public void deleteAllInBatch() {

        }

        @Override
        public AppUser getOne(Long aLong) {
            return null;
        }

        @Override
        public AppUser getById(Long aLong) {
            return null;
        }

        @Override
        public AppUser getReferenceById(Long aLong) {
            return null;
        }

        @Override
        public <S extends AppUser> List<S> findAll(Example<S> example) {
            return null;
        }

        @Override
        public <S extends AppUser> List<S> findAll(Example<S> example, Sort sort) {
            return null;
        }

        @Override
        public <S extends AppUser> List<S> saveAll(Iterable<S> entities) {
            return null;
        }

        @Override
        public List<AppUser> findAll() {
            return null;
        }

        @Override
        public List<AppUser> findAllById(Iterable<Long> longs) {
            return null;
        }

        @Override
        public <S extends AppUser> S save(S entity) {
            return null;
        }

        @Override
        public Optional<AppUser> findById(Long aLong) {
            return Optional.empty();
        }

        @Override
        public boolean existsById(Long aLong) {
            return false;
        }

        @Override
        public long count() {
            return 0;
        }

        @Override
        public void deleteById(Long aLong) {

        }

        @Override
        public void delete(AppUser entity) {

        }

        @Override
        public void deleteAllById(Iterable<? extends Long> longs) {

        }

        @Override
        public void deleteAll(Iterable<? extends AppUser> entities) {

        }

        @Override
        public void deleteAll() {

        }

        @Override
        public List<AppUser> findAll(Sort sort) {
            return null;
        }

        @Override
        public Page<AppUser> findAll(Pageable pageable) {
            return null;
        }

        @Override
        public <S extends AppUser> Optional<S> findOne(Example<S> example) {
            return Optional.empty();
        }

        @Override
        public <S extends AppUser> Page<S> findAll(Example<S> example, Pageable pageable) {
            return null;
        }

        @Override
        public <S extends AppUser> long count(Example<S> example) {
            return 0;
        }

        @Override
        public <S extends AppUser> boolean exists(Example<S> example) {
            return false;
        }

        @Override
        public <S extends AppUser, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
            return null;
        }
    };
    private final static String USER_NOT_FOUND_MSG = "User with email %s not found";


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username).
                orElseThrow(() ->
                        new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG,username)));
    }
}
