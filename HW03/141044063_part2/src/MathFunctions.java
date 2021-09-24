public class MathFunctions {

    public float sin(int degreeComplete){
        int degree = degreeComplete % 360;
        float result = 0.0f;
        if (degree == 0)
            result = 0.0000f;
        else if(degree>0 && degree <= 10)
            result = 0.0872f;
        else if(degree> 10 && degree <=20)
            result = 0.2588f;
        else if(degree> 20 && degree <30)
            result = 0.4226f;
        else if(degree == 30)
            result = 0.5000f;
        else if(degree> 30 && degree <=40)
            result = 0.5736f;
        else if(degree> 40 && degree <=50)
            result = 0.7071f;
        else if(degree> 50 && degree <=60)
            result = 0.8192f;
        else if(degree == 60)
            result = 0.8660f;
        else if(degree> 60 && degree <=70)
            result = 0.9063f;
        else if(degree> 70 && degree <=80)
            result = 0.9659f;
        else if(degree> 80 && degree <90)
            result = 0.9962f;
        else if(degree == 90)
            result = 1.0000f;
        else if(degree> 90 && degree <=100)
            result = 0.9962f;
        else if(degree> 100 && degree <=110)
            result = 0.9659f;
        else if(degree> 110 && degree <120)
            result = 0.9063f;
        else if(degree == 120)
            result = 0.8660f;
        else if(degree> 120 && degree <=130)
            result = 0.8192f;
        else if(degree> 130 && degree <=140)
            result = 0.7071f;
        else if(degree> 140 && degree <150)
            result = 0.5000f;
        else if(degree == 150)
            result = 0.8660f;
        else if(degree> 150 && degree <=160)
            result = 0.4226f;
        else if(degree> 160 && degree <=170)
            result = 0.2588f;
        else if(degree> 170 && degree <180)
            result = 0.0872f;
        else if(degree == 180)
            result = 0.0000f;
        else if(degree> 180 && degree <=190)
            result = -0.0872f;
        else if(degree> 190 && degree <=200)
            result = -0.2588f;
        else if(degree> 200 && degree <210)
            result = -0.4226f;
        else if(degree == 210)
            result = -0.5000f;
        else if(degree> 210 && degree <=220)
            result = -0.5736f;
        else if(degree> 220 && degree <=230)
            result = -0.7071f;
        else if(degree> 230 && degree <240)
            result = -0.8192f;
        else if(degree == 240)
            result = -0.8660f;
        else if(degree> 240 && degree <=250)
            result = -0.9063f;
        else if(degree> 250 && degree <=260)
            result = -0.9659f;
        else if(degree> 260 && degree <270)
            result = -0.9962f;
        else if(degree == 270)
            result = -1.0000f;
        else if(degree> 270 && degree <=280)
            result = -0.9962f;
        else if(degree> 280 && degree <=290)
            result = -0.9659f;
        else if(degree> 290 && degree <300)
            result = -0.9063f;
        else if(degree == 300)
            result = -0.8660f;
        else if(degree> 300 && degree <=310)
            result = -0.8192f;
        else if(degree> 310 && degree <=320)
            result = -0.7071f;
        else if(degree> 320 && degree <330)
            result = -0.5000f;
        else if(degree == 330)
            result = -0.8660f;
        else if(degree> 330 && degree <=340)
            result = -0.4226f;
        else if(degree> 340 && degree <=350)
            result = -0.2588f;
        else if(degree> 350 && degree <360)
            result = -0.0872f;
        return result;
    }


    public float cos(int degreeComplete){
        int degree = degreeComplete % 360;
        float result = 0.0f;
        if (degree == 0)
            result = 1.0000f;
        else if(degree>0 && degree <= 10)
            result = 0.9962f;
        else if(degree> 10 && degree <=20)
            result = 0.9659f;
        else if(degree> 20 && degree <30)
            result = 0.9063f;
        else if(degree == 30)
            result = 0.8660f;
        else if(degree> 30 && degree <=40)
            result = 0.8192f;
        else if(degree> 40 && degree <=50)
            result = 0.7071f;
        else if(degree> 50 && degree <=60)
            result = 0.5736f;
        else if(degree == 60)
            result = 0.5000f;
        else if(degree> 60 && degree <=70)
            result = 0.4226f;
        else if(degree> 70 && degree <=80)
            result = 0.2588f;
        else if(degree> 80 && degree <90)
            result = 0.0872f;
        else if(degree == 90)
            result = 0.0000f;
        else if(degree> 90 && degree <=100)
            result = -0.0872f;
        else if(degree> 100 && degree <=110)
            result = -0.2588f;
        else if(degree> 110 && degree <120)
            result = -0.4226f;
        else if(degree == 120)
            result = -0.5000f;
        else if(degree> 120 && degree <=130)
            result = -0.5736f;
        else if(degree> 130 && degree <=140)
            result = -0.7071f;
        else if(degree> 140 && degree <150)
            result = -0.8192f;
        else if(degree == 150)
            result = -0.8660f;
        else if(degree> 150 && degree <=160)
            result = -0.9063f;
        else if(degree> 160 && degree <=170)
            result = -0.9659f;
        else if(degree> 170 && degree <180)
            result = -0.9962f;
        else if(degree == 180)
            result = -1.0000f;
        else if(degree> 180 && degree <=190)
            result = -0.9962f;
        else if(degree> 190 && degree <=200)
            result = -0.9659f;
        else if(degree> 200 && degree <210)
            result = -0.9063f;
        else if(degree == 210)
            result = -0.8660f;
        else if(degree> 210 && degree <=220)
            result = -0.8192f;
        else if(degree> 220 && degree <=230)
            result = -0.7071f;
        else if(degree> 230 && degree <240)
            result = -0.5736f;
        else if(degree == 240)
            result = -0.5000f;
        else if(degree> 240 && degree <=250)
            result = -0.4226f;
        else if(degree> 250 && degree <=260)
            result = -0.2588f;
        else if(degree> 260 && degree <270)
            result = -0.0872f;
        else if(degree == 270)
            result = 0.0000f;
        else if(degree> 270 && degree <=280)
            result = 0.0872f;
        else if(degree> 280 && degree <=290)
            result = 0.2588f;
        else if(degree> 290 && degree <300)
            result = 0.4226f;
        else if(degree == 300)
            result = 0.5000f;
        else if(degree> 300 && degree <=310)
            result = 0.6428f;
        else if(degree> 310 && degree <=320)
            result = 0.7071f;
        else if(degree> 320 && degree <330)
            result = 0.8192f;
        else if(degree == 330)
            result = 0.8660f;
        else if(degree> 330 && degree <=340)
            result = 0.9063f;
        else if(degree> 340 && degree <=350)
            result = 0.9659f;
        else if(degree> 350 && degree <360)
            result = 0.9962f;
        return result;
    }

    public float abs(float number){
        if(number < 0)
            return  number * (-1);
        else
            return number;
    }
}

