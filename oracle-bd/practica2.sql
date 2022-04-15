
/*
	El usuario y contraseña es la misma   ->  practica2 
*/



/*  TABLAS
*/




-- CREATE USER practica2 IDENTIFIED BY ACCOUNT UNLOCK ;

-- predefined type, no DDL - MDSYS.SDO_GEOMETRY

-- predefined type, no DDL - XMLTYPE

CREATE TABLE practica2.clientes (
    dni_cl        NUMBER NOT NULL,
    nombre_cl     VARCHAR2(30 BYTE),
    apellido_1    VARCHAR2(30 BYTE),
    apellido_2    VARCHAR2(20 BYTE),
    clase_via     VARCHAR2(20 BYTE),
    numero_via    NUMBER,
    cod_postal    NUMBER,
    ciudad        VARCHAR2(20 BYTE),
    telefono      NUMBER,
    observaciones VARCHAR2(20 BYTE),
    nombre_via    VARCHAR2(20 BYTE),
    CONSTRAINT clientes_pk PRIMARY KEY ( dni_cl )
);


CREATE TABLE practica2.companias (
    nombre_compania       VARCHAR2(20 BYTE) NOT NULL,
    clase_via             VARCHAR2(20 BYTE),
    nombre_via            VARCHAR2(20 BYTE),
    numero_via            NUMBER,
    cod_postal            NUMBER,
    telefono_contratacion NUMBER,
    telefono_siniestros   NUMBER,
    notas                 VARCHAR2(20 BYTE),
    CONSTRAINT companias_pk PRIMARY KEY ( nombre_compania )
);

CREATE TABLE practica2.seguros (
    numero_poliza            NUMBER NOT NULL,
    ramo                     VARCHAR2(30 BYTE),
    fecha_inicio             DATE,
    fecha_vencimiento        DATE,
    condiciones_particulares VARCHAR2(30 BYTE),
    dni_cl                   NUMBER,
    CONSTRAINT seguros_pk PRIMARY KEY ( numero_poliza ),
    CONSTRAINT seguros_fk1 FOREIGN KEY ( dni_cl ) REFERENCES practica2.clientes ( dni_cl )
            ON DELETE CASCADE
);


CREATE TABLE practica2.companias_seguros (
    numero_poliza   NUMBER NOT NULL,
    nombre_compania VARCHAR2(20 BYTE),
    id              NUMBER NOT NULL,
    CONSTRAINT companias_seguros_pk PRIMARY KEY ( id ),
    CONSTRAINT companias_seguros_fk1 FOREIGN KEY ( numero_poliza ) REFERENCES practica2.seguros ( numero_poliza )
            ON DELETE CASCADE,
    CONSTRAINT companias_seguros_fk2 FOREIGN KEY ( nombre_compania ) REFERENCES practica2.companias ( nombre_compania )
            ON DELETE CASCADE
    
);


CREATE TABLE practica2.peritos (
    dni_perito        NUMBER NOT NULL,
    nombre_perito     VARCHAR2(20 BYTE),
    apellido_perito1  VARCHAR2(20 BYTE),
    apellido_perito2  VARCHAR2(20 BYTE),
    telefono_contacto NUMBER,
    telefono_oficina  NUMBER,
    clase_via         VARCHAR2(20 BYTE),
    nombre_via        VARCHAR2(20 BYTE),
    numero_via        NUMBER,
    cod_postal        NUMBER,
    ciudad            VARCHAR2(20 BYTE),
    CONSTRAINT peritos_pk PRIMARY KEY ( dni_perito )
);



CREATE TABLE practica2.siniestros (
    id_siniestro    NUMBER NOT NULL,
    fecha_siniestro DATE,
    causas          VARCHAR2(20 BYTE),
    aceptado        VARCHAR2(20 BYTE),
    indemnizacion   NUMBER,
    numero_poliza   NUMBER,
    dni_perito      NUMBER,
    CONSTRAINT siniestros_pk PRIMARY KEY ( id_siniestro ),
    CONSTRAINT siniestros_fk1 FOREIGN KEY ( numero_poliza ) REFERENCES practica2.seguros ( numero_poliza )
            ON DELETE CASCADE,
    CONSTRAINT siniestros_fk2 FOREIGN KEY ( dni_perito ) REFERENCES practica2.peritos ( dni_perito )
            ON DELETE CASCADE
);





 /*Activar el imprimir en consola de sql*/
    SET serveroutput ON  


/*

                PROCEDIMIENTOS*


*/
CREATE or replace PROCEDURE ingreso_poliza (
    ramo         IN VARCHAR2,
    fechain      IN DATE,
    fechafin     IN DATE,
    condiciones  IN VARCHAR2,
    dnicl        IN NUMBER
) IS
    nuevaPoliza number :=0;
BEGIN
    
    nuevaPoliza := sequencia_seguros.Nextval();
    
    INSERT INTO "PRACTICA2"."SEGUROS" (
        numero_poliza,
        ramo,
        fecha_inicio,
        fecha_vencimiento,
        condiciones_particulares,
        dni_cl
    ) VALUES (
        nuevaPoliza,
        ramo,
        fechain,
        fechafin,
        condiciones,
        dnicl
    );

END;

CREATE or replace PROCEDURE  ingreso_poliza_retorno (
    numeropoliza OUT NUMBER,
    ramo         IN VARCHAR2,
    fechain      IN DATE,
    fechafin     IN DATE,
    condiciones  IN VARCHAR2,
    dnicl        IN OUT NUMBER
) IS
BEGIN
        
    numeropoliza:= sequencia_seguros.Nextval();
    
    
    INSERT INTO "PRACTICA2"."SEGUROS" (
        numero_poliza,
        ramo,
        fecha_inicio,
        fecha_vencimiento,
        condiciones_particulares,
        dni_cl
    ) VALUES (
        numeropoliza,
        ramo,
        fechain,
        fechafin,
        condiciones,
        dnicl
    );

END;

CREATE OR REPLACE PROCEDURE eliminarcliente (
    dniVal IN NUMBER
) IS
BEGIN
    DELETE FROM clientes
    WHERE
        clientes.dni_cl = dniVal;
END;

/*
                SECUENCIA*

*/
CREATE SEQUENCE sequencia_clientes INCREMENT BY 1 START WITH 2;

CREATE SEQUENCE sequencia_seguros INCREMENT BY 1 START WITH 41;

CREATE SEQUENCE sequencia_siniestros INCREMENT BY 1 START WITH 1;

CREATE SEQUENCE sequencia_peritos INCREMENT BY 1 START WITH 4;

CREATE SEQUENCE sequencia_companias_seguros INCREMENT BY 1 START WITH 1;

/*

                EJECUCIÓN*


*/

BEGIN
    ingreso_poliza( 'Vida', TO_DATE('2022-01-11 23:46:24', 'YYYY-MM-DD HH24:MI:SS'), TO_DATE('2022-01-11 23:46:30',
                   'YYYY-MM-DD HH24:MI:SS'), 'N/A', 1);
END;

DECLARE
    polizanumber NUMBER;
    dnicliente   NUMBER;
BEGIN
    dnicliente := '4';
    ingreso_poliza_retorno(polizanumber, 'Vida', TO_DATE('2022-01-11 23:46:24', 'YYYY-MM-DD HH24:MI:SS'), TO_DATE('2022-01-11 23:46:30',
                   'YYYY-MM-DD HH24:MI:SS'), 'N/A', dnicliente);

    dbms_output.put_line('valor : ' || polizanumber);
    dbms_output.put_line('valor : ' || dnicliente);
END;


eliminarCliente(151210);



/*
    FUNCIONES*
*/
create or replace package  practica2 is


function func_ingreso_poliza_retorno( 
    ramo         IN VARCHAR2,
    fechain      IN DATE,
    fechafin     IN DATE,
    condiciones  IN VARCHAR2,
    dnicl        IN NUMBER
) return number;

end practica2;

create or replace package body practica2 is


function func_ingreso_poliza_retorno( 
    ramo         IN VARCHAR2,
    fechain      IN DATE,
    fechafin     IN DATE,
    condiciones  IN VARCHAR2,
    dnicl        IN NUMBER
) return number 
is
    numeropoliza number:=0;
BEGIN
        
    select max(numero_poliza)+1 into numeropoliza from seguros;
    
    
    INSERT INTO "PRACTICA2"."SEGUROS" (
        numero_poliza,
        ramo,
        fecha_inicio,
        fecha_vencimiento,
        condiciones_particulares,
        dni_cl
    ) VALUES (
        numeropoliza,
        ramo,
        fechain,
        fechafin,
        condiciones,
        dnicl
    );
    return numeropoliza;
END;
end practica2;

DECLARE
    polizanumber NUMBER;
    dnicliente   NUMBER;
BEGIN
    dnicliente := '1';
    polizanumber:= practica2.func_ingreso_poliza_retorno( 'Vida', TO_DATE('2022-01-11 23:46:24', 'YYYY-MM-DD HH24:MI:SS'), TO_DATE('2022-01-11 23:46:30',
                   'YYYY-MM-DD HH24:MI:SS'), 'N/A', dnicliente);

    dbms_output.put_line('valor : ' || polizanumber);
    dbms_output.put_line('valor : ' || dnicliente);
END;


