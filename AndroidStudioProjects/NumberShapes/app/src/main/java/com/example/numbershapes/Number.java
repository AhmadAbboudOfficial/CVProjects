package com.example.numbershapes;

public class Number {

    public int number;

    public boolean isTriangular(){
        int triangular_number = 1;
        int counter = 1;

        while (triangular_number < number){
            counter++;
            triangular_number += counter;

            if (number == triangular_number){
                return true;
            }
        }
        return false;
    }

    public boolean isSquare(){
        double squareRoot = Math.sqrt(number);

        if (squareRoot == Math.floor(squareRoot)){
            return true;
        }
        return false;
    }
}
