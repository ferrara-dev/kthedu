cmake_minimum_required(VERSION 3.16)
set(CMAKE_TRY_COMPILE_TARGET_TYPE "STATIC_LIBRARY")
project(ls C)

set(CMAKE_C_STANDARD 99)

add_executable(ls main.c datastructure/Node.h
        datastructure/Node.c
        datastructure/Stack.c
        datastructure/Stack.h
        )