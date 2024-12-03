package com.poll.service.impl;

import com.poll.mapper.ImageMapper;
import com.poll.pojo.Image;
import com.poll.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImageServiceImpl implements ImageService {

    private final ImageMapper imageMapper;

    public ImageServiceImpl(ImageMapper imageMapper) {
        this.imageMapper = imageMapper;
    }

    @Override
    public void insert(Image image) {
        imageMapper.insert(image);
    }
}
