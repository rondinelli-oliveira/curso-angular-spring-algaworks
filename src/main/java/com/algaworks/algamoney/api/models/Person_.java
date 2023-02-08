package com.algaworks.algamoney.api.models;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Person.class)
public abstract class Person_ {

    public static volatile SingularAttribute<Person, Long> id;
    public static volatile SingularAttribute<Person, Boolean> status;
    public static volatile SingularAttribute<Person, Adress> adress;
    public static volatile SingularAttribute<Person, String> name;
}
