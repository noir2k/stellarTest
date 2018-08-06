package net.hiskarma.stellarTest.module.common.service.impl;

import net.hiskarma.stellarTest.module.common.service.UserService;
import net.hiskarma.stellarTest.persistence.model.mapper.UserDto;
import net.hiskarma.stellarTest.persistence.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public Page<UserDto> findUserDtoList(Integer page, String name) {
        return userRepository.findByUserPaging(name, pageRequestByPage(page))
                .map(user -> modelMapper.map(name, UserDto.class));
    }

    private PageRequest pageRequestByPage(int page) {
        return PageRequest.of(page, 10, Sort.by(Sort.Direction.DESC, "createDateTime"));
    }
}
