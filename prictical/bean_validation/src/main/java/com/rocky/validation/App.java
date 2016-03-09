package com.rocky.validation;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;


/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {


        Employee employee = new Employee();
        employee.setName("Zhang Guan Nan");

        //Single Constraints
        //companyʹ�õ����Զ����NotEmpty��ǩ
        String company = new String();
        employee.setCompany(company);

        //Multiple Constraints
        //placeʹ�õ����Զ����PatternOfString��ǩ
        String place = "C";
        employee.setPlace(place);

        String hobby = new String();
        employee.setHobby(hobby);

        //Object Graph ������֤
        Address address = new Address();
        address.setProvince("");
        address.setCity("City");
        employee.setAddress(address);

        ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
        Validator validator = vf.getValidator();
        Set<ConstraintViolation<Employee>> set = validator.validate(employee);
        for (ConstraintViolation<Employee> constraintViolation : set) {
            System.out.println(constraintViolation.getMessage());
        }

    }
}
