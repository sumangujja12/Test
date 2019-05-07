package com.multibrand.vo.response;

import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.stereotype.Component;

@XmlRootElement(name="validPassword")
@Component
public class PasswordValidityResponse extends GenericResponse{

}
