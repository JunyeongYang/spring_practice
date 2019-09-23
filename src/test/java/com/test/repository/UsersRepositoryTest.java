package com.test.repository;

import com.test.domain.Users;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UsersRepositoryTest {

    @Autowired
    private UsersRepository usersRepository;

    @Test
    public void save() {
        usersRepository.save(new Users("8959.kr@gmail.com", "1q2w3e4r"));
        Users selectedUser = usersRepository.getOne("8959.kr@gmail.com");
        Assert.assertEquals("8959.kr@gmail.com", selectedUser.getId());
    }
}