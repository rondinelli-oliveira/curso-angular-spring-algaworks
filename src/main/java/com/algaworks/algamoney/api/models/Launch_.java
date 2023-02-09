package com.algaworks.algamoney.api.models;

import com.algaworks.algamoney.api.models.enums.TypeLaunch;
//import jakarta.annotation.Generated;
//import jakarta.persistence.metamodel.SingularAttribute;
//import jakarta.persistence.metamodel.StaticMetamodel;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import java.math.BigDecimal;
import java.time.LocalDate;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Launch.class)
public class Launch_ {

    public static volatile SingularAttribute<Launch, Long> id;
    public static volatile SingularAttribute<Launch, String> note;
    public static volatile SingularAttribute<Launch, TypeLaunch> type;
    public static volatile SingularAttribute<Launch, LocalDate> payday;
    public static volatile SingularAttribute<Launch, Person> person;
    public static volatile SingularAttribute<Launch, LocalDate> dueDate;
    public static volatile SingularAttribute<Launch, Category> category;
    public static volatile SingularAttribute<Launch, BigDecimal> value;
    public static volatile SingularAttribute<Launch, String> description;
}
