package com.algaworks.algamoney.api.models;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Adress.class)
public abstract class Adress_ {

    public static volatile SingularAttribute<Adress, String> city;
    public static volatile SingularAttribute<Adress, String> state;
    public static volatile SingularAttribute<Adress, String> complement;
    public static volatile SingularAttribute<Adress, String> number;
    public static volatile SingularAttribute<Adress, String> street;
    public static volatile SingularAttribute<Adress, String> district;
    public static volatile SingularAttribute<Adress, String> zipCode;
}
