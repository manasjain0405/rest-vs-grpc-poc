package io.github.rest.service;

import org.springframework.stereotype.Service;

@Service
public class SquareService {

    public int getSquare (int no) {
        return no*no;
    }
}
