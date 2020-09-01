cmake_minimum_required(VERSION 3.16)
project(E11 C)

set(CMAKE_C_STANDARD 99)

add_executable(
        E11 main.c
        Node.c
        Node.h
        Stack.c
        Stack.h
        )