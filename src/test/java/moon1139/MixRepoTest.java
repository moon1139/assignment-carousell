package moon1139;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import moon1139.entity.User;
import moon1139.repository.UserRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class MixRepoTest {
	@Autowired
    private TestEntityManager entityManager;
    
    @Autowired
    private UserRepository users;

    @Test
    public void testFindByUserName() {
        User user = new User("theName");
        entityManager.persist(user);

        List<User> findByUserName = users.findByUserName(user.getUsername());

        assertThat(findByUserName).extracting(User::getUsername).containsOnly(user.getUsername());
    }
}
