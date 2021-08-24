-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 24-08-2021 a las 02:05:31
-- Versión del servidor: 10.4.17-MariaDB
-- Versión de PHP: 8.0.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `facturacion`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `articulos`
--

CREATE TABLE `articulos` (
  `ID_ART` varchar(6) NOT NULL,
  `DES_ART` varchar(80) NOT NULL,
  `MARCA` varchar(20) NOT NULL,
  `PAIS` varchar(20) DEFAULT NULL,
  `STOCK` int(10) NOT NULL,
  `PVP_ART` double(10,2) NOT NULL,
  `PCO_ART` double(10,2) NOT NULL,
  `EX_MAX` int(10) NOT NULL,
  `EX_MIN` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `articulos`
--

INSERT INTO `articulos` (`ID_ART`, `DES_ART`, `MARCA`, `PAIS`, `STOCK`, `PVP_ART`, `PCO_ART`, `EX_MAX`, `EX_MIN`) VALUES
('AR0001', 'TELEVISOR FLATRON XS32 SONY', 'SONY', 'JAPON', 45, 2500.50, 1800.00, 50, 5),
('AR0002', 'REFRIGERADORA ECASA 16 PIES FAMILIA', 'ECASA', 'ECUADOR', 25, 1100.25, 950.25, 30, 8),
('AR0003', 'LICUADORA 5 VELOCIDADES OSTER', 'OSTER', 'CHILE', 54, 125.50, 78.00, 60, 10),
('AR0004', 'DVD LG SGX134', 'LG', 'INDONESIA', 50, 180.50, 150.50, 60, 15),
('AR0005', 'DVD S34E SONY', 'SONY', 'JAPON', 30, 220.56, 195.25, 45, 10),
('AR0006', 'LAVADORA LG 16LLG', 'LG', 'INDONESIA', 25, 850.50, 705.50, 45, 15),
('AR0007', 'LAVADORA ELECTROLUX 18XLBL', 'ELECTROLUX', 'USA', 44, 950.45, 790.00, 60, 10),
('AR0008', 'COCINA INDURAMA ROMA', 'INDURAMA', 'CHILE', 0, 650.25, 520.20, 30, 10),
('AR0009', 'COCINA INDURAMA VENECIA', 'INDURAMA', 'CHILE', 26, 780.50, 595.25, 45, 15),
('AR0010', 'TELEVISOR PHILLIPS 27PP', 'PHILLIPS', 'USA', 35, 950.60, 780.45, 60, 15),
('AR0011', 'TELEVISOR AYWA 52HD', 'AYWA', 'JAPON', 30, 2150.50, 1900.25, 60, 10),
('AR0012', 'EQUIPO DE SONIDO SONY 8000WPS', 'SONY', 'JAPON', 50, 1350.50, 990.50, 60, 10),
('AR0013', 'EQUIPO DE SONIDO AYWA 7500 PMP', 'AYWA', 'JAPON', 50, 1050.50, 780.26, 60, 15),
('AR0014', 'LICUADORA 8 VEL BLACK AND DECKER', 'BLACK&DECKER', 'CANADA', 48, 85.48, 58.45, 50, 10),
('AR0015', 'PROCESADOR DE ALIMENTOS OSTER', 'OSTER', 'CHILE', 45, 98.00, 80.25, 50, 15),
('AR0016', 'TOSTADORA XS15 OSTER', 'OSTER', 'CHILE', 36, 45.00, 35.50, 45, 15),
('AR0017', 'TELEVISOR SAMSUNG 42P', 'SAMSUNG', 'KOREA', 30, 1850.50, 1500.00, 45, 10),
('AR0018', 'REFRIGERADORA ELECTROLUX NF21', 'ELECTROLUX', 'CHILE', 20, 1050.75, 850.50, 35, 10),
('AR0019', 'MICROONDAS LG XRT12', 'LG', 'INDONESIA', 15, 250.50, 158.90, 35, 10),
('AR0020', 'MICROONDAS OSTER OM12WW', 'OSTER', 'CHILE', 17, 180.25, 154.25, 25, 10),
('AR0021', 'GRABADORA SONY 300WW SN300', 'SONY', 'JAPON', 25, 350.00, 330.00, 50, 10),
('AR0022', 'GRABADORA LG 500WLG', 'LG', 'INDONESIA', 24, 420.00, 395.50, 60, 10),
('AR0023', 'PROCESADOR DE ALIMENTOS BD', 'BLACK&DECKER', 'CANADA', 15, 105.50, 89.65, 50, 10),
('AR0024', 'TOSTADORA OSTER OS50', 'OSTER', 'CHILE', 35, 85.50, 70.50, 60, 10),
('AR0025', 'BATIDORA OSTER 15VOS', 'OSTER', 'CHILE', 45, 105.50, 90.00, 50, 10);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `clientes`
--

CREATE TABLE `clientes` (
  `ID_CLI` varchar(6) NOT NULL,
  `NOM_CLI` varchar(30) NOT NULL,
  `APE_CLI` varchar(30) NOT NULL,
  `DIR_CLI` varchar(80) NOT NULL,
  `CER_CLI` varchar(13) NOT NULL,
  `TEL_CLI` varchar(9) DEFAULT NULL,
  `CEL_CLI` varchar(9) DEFAULT NULL,
  `SEX_CLI` varchar(9) NOT NULL,
  `EMA_CLI` varchar(100) DEFAULT NULL,
  `SAL_CLI` double(10,2) NOT NULL,
  `FEC_ING` date NOT NULL,
  `ACTIVO` tinyint(1) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `clientes`
--

INSERT INTO `clientes` (`ID_CLI`, `NOM_CLI`, `APE_CLI`, `DIR_CLI`, `CER_CLI`, `TEL_CLI`, `CEL_CLI`, `SEX_CLI`, `EMA_CLI`, `SAL_CLI`, `FEC_ING`, `ACTIVO`) VALUES
('CL0001', 'MIGUEL EDUARDO', 'MEDINA RODRIGUEZ', 'AV JAVIER SOTO Y OLMEDO (SANTA ROSA)', '0702265487', '072948588', '095784122', 'MASCULINO', 'lkhlkjhlkjhlkjhkghg@hotmail.com', 0.00, '2011-07-07', 1),
('CL0002', 'LUISA MARIA', 'FERNANDEZ SAAVEDRA', 'AV LAS PALMERAS Y OCTAVA NORTE (MACHALA)', '0706521458', '072654854', '084662533', 'FEMENINO', 'luisa@hotmail.com', 0.00, '2011-07-07', 0),
('CL0003', 'KAREN FABIOLA', 'QUEZADA MOLINA', 'ROCAFUERTE Y OCHOA LEON (PASAJE)', '0706989899', '072945544', '098654123', 'FEMENINO', 'karenfabiola@hotmail.es', 0.00, '2011-07-07', 1),
('CL0004', 'CARLOS EDUARDO', 'SANTANA PALADINES', 'AV LA REPUBLICA Y ECUADOR (ARENILLAS)', '0706532122', '072654888', '094652111', 'MASCULINO', 'carlossantana@hotmail.com', 0.00, '2011-07-07', 1),
('CL0005', 'SAUL FRANCISC', 'MONTALVAN SALAZAR', 'BARRIO EL PERIODISTA MZ5 (HUAQUILLAS)', '3333333333333', '072946350', '094652322', 'MASCULINO', 'saulpancho@hotmail.com', 0.00, '2011-07-07', 1),
('CL0006', 'BERTHA LEONOR', 'AGUIRRE ARMIJOS', 'CDLA JUANA DE ORO KM 1 VIA A MACHALA (PASAJE)', '0706598666', '', '095369366', 'FEMENINO', 'bertha_leonor@hotmail.com', 0.00, '2011-07-20', 1),
('CL0007', 'JUAN LEONARDO', 'GALLEGOS DELGADO', 'SUCRE Y 30 DE AGOSTO (SANTA ROSA)', '3333333333333', '072946350', '094652322', 'MASCULINO', 'juanga_delgado@hotmail.com', 0.00, '2011-07-21', 1),
('CL0008', 'PATRICIO FERNANDO', 'SANMARTIN ROMERO', 'JUNIN Y PICHINCHA (MACHALA)', '0704656122', '072655555', '', 'MASCULINO', 'patosanmartin@hotmail.com', 0.00, '2011-07-23', 0),
('CL0009', 'GABRIELA SABRINA', 'LOZANO ERAS', 'ROCAFUERTE Y 25 DE JUNIO (MACHALA)', '0703632114', '', '098754122', 'FEMENINO', 'gabysabrina@hotmail.com', 0.00, '2011-07-25', 0),
('CL0010', 'EDUARDO RODRIGO', 'RENTERIA JARAMILLO', 'BARRIO GALAPAGOS (SANTA ROSA)', '0706565655', '072658922', '095698000', 'MASCULINO', 'eduardo_renteriajar@hotmail.com', 0.00, '2011-07-25', 0),
('CL0011', 'sdfas', 'sdfas', 'sdafasd', 'sadfasdf', '', 'sdfasdf', 'MASCULINO', '', 16.55, '2013-01-13', 0),
('CL0012', 'testing', 'apelido', 'desconocida', '321654', '456789', '123456987', 'MASCULINO', 'adas@testing.com', 58.97, '2013-01-14', 0),
('CL0013', 'otrotesting', 'desconodic', 'quiensabe', '123456', '9876325', '987885', 'MASCULINO', 'sadfasdfdfsd', 8.89, '2013-01-14', 0),
('CL0014', 'sdfasdf', 'sdfsf', 'asdfasd', '2341234123', '24123412', '341234123', 'MASCULINO', '', 2341223.00, '2013-01-14', 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `facv_cab`
--

CREATE TABLE `facv_cab` (
  `NUM_FACV` varchar(10) NOT NULL,
  `FECHA` date NOT NULL,
  `ID_CLI` varchar(6) NOT NULL,
  `ID_VEN` varchar(6) NOT NULL,
  `FOR_PAG` varchar(7) NOT NULL,
  `DESCU` int(2) NOT NULL,
  `SUB_GEN` double(10,2) NOT NULL,
  `TOT_DES` double(10,2) NOT NULL,
  `TOT_IVA` double(10,2) NOT NULL,
  `OBSERVAC` varchar(100) DEFAULT NULL,
  `ANULADA` int(1) NOT NULL DEFAULT 0,
  `PAGO_INI` double(10,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `facv_cab`
--

INSERT INTO `facv_cab` (`NUM_FACV`, `FECHA`, `ID_CLI`, `ID_VEN`, `FOR_PAG`, `DESCU`, `SUB_GEN`, `TOT_DES`, `TOT_IVA`, `OBSERVAC`, `ANULADA`, `PAGO_INI`) VALUES
('0000000001', '2013-01-11', 'CL0007', 'VE0004', 'CONTADO', 0, 1050.50, 0.00, 126.06, '', 0, 1176.56),
('0000000002', '2013-01-11', 'CL0001', 'VE0001', 'CONTADO', 0, 3531.25, 0.00, 423.75, '', 0, 3955.00),
('0000000003', '2013-01-11', 'CL0004', 'VE0001', 'CONTADO', 0, 4401.50, 0.00, 528.18, '', 0, 4929.68),
('0000000004', '2013-01-11', 'CL0001', 'VE0001', 'CONTADO', 0, 4201.50, 0.00, 504.18, '', 1, 4705.68),
('0000000005', '2013-01-08', 'CL0001', 'VE0001', 'CONTADO', 0, 180.50, 0.00, 21.66, '', 1, 202.16),
('0000000006', '2013-01-11', 'CL0001', 'VE0002', 'CONTADO', 0, 180.50, 0.00, 21.66, '', 0, 202.16),
('0000000007', '2013-01-19', 'CL0007', 'VE0004', 'CONTADO', 0, 2000.95, 0.00, 240.11, '', 0, 2241.06),
('0000000008', '2013-01-19', 'CL0001', 'VE0002', 'CONTADO', 0, 2721.06, 0.00, 326.53, '', 0, 3047.59),
('0000000009', '2013-01-19', 'CL0001', 'VE0002', 'CONTADO', 0, 1600.70, 0.00, 192.08, '', 0, 1792.78),
('0000000010', '2013-01-19', 'CL0004', 'VE0001', 'CONTADO', 0, 830.75, 0.00, 99.69, '', 0, 930.44),
('0000000011', '2013-01-19', 'CL0005', 'VE0002', 'CONTADO', 0, 1500.75, 0.00, 180.09, '', 0, 1680.84),
('0000000012', '2013-01-19', 'CL0001', 'VE0001', 'CONTADO', 0, 2751.25, 0.00, 330.15, '', 0, 3081.40),
('0000000013', '2013-01-19', 'CL0003', 'VE0002', 'CONTADO', 0, 1130.70, 0.00, 135.68, '', 0, 1266.38),
('0000000014', '2013-01-11', 'CL0003', 'VE0002', 'CONTADO', 0, 220.56, 0.00, 26.47, '', 0, 247.03),
('0000000015', '2013-01-11', 'CL0001', 'VE0003', 'CONTADO', 0, 180.50, 0.00, 21.66, '', 0, 202.16);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `facv_det`
--

CREATE TABLE `facv_det` (
  `NUM_DET` int(10) NOT NULL,
  `ID_ART` varchar(6) NOT NULL,
  `PVP` double(10,2) NOT NULL,
  `CANT` int(10) NOT NULL,
  `NUM_FACV` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `facv_det`
--

INSERT INTO `facv_det` (`NUM_DET`, `ID_ART`, `PVP`, `CANT`, `NUM_FACV`) VALUES
(1, 'AR0013', 1050.50, 1, '0000000001'),
(2, 'AR0001', 2500.50, 1, '0000000002'),
(3, 'AR0006', 850.50, 1, '0000000002'),
(4, 'AR0020', 180.25, 1, '0000000002'),
(5, 'AR0006', 850.50, 1, '0000000003'),
(6, 'AR0001', 2500.50, 1, '0000000003'),
(7, 'AR0013', 1050.50, 1, '0000000003'),
(8, 'AR0001', 2500.50, 1, '0000000004'),
(9, 'AR0008', 650.25, 1, '0000000004'),
(10, 'AR0018', 1050.75, 1, '0000000004'),
(11, 'AR0004', 180.50, 1, '0000000005'),
(12, 'AR0004', 180.50, 1, '0000000006'),
(13, 'AR0013', 1050.50, 1, '0000000007'),
(14, 'AR0007', 950.45, 1, '0000000007'),
(15, 'AR0001', 2500.50, 1, '0000000008'),
(16, 'AR0005', 220.56, 1, '0000000008'),
(17, 'AR0007', 950.45, 1, '0000000009'),
(18, 'AR0008', 650.25, 1, '0000000009'),
(19, 'AR0004', 180.50, 1, '0000000010'),
(20, 'AR0008', 650.25, 1, '0000000010'),
(21, 'AR0006', 850.50, 1, '0000000011'),
(22, 'AR0008', 650.25, 1, '0000000011'),
(23, 'AR0008', 650.25, 1, '0000000012'),
(24, 'AR0017', 1850.50, 1, '0000000012'),
(25, 'AR0019', 250.50, 1, '0000000012'),
(26, 'AR0007', 950.45, 1, '0000000013'),
(27, 'AR0020', 180.25, 1, '0000000013'),
(28, 'AR0005', 220.56, 1, '0000000014'),
(29, 'AR0004', 180.50, 1, '0000000015');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `vendedores`
--

CREATE TABLE `vendedores` (
  `ID_VEN` varchar(6) NOT NULL,
  `NOM_VEN` varchar(30) NOT NULL,
  `APE_VEN` varchar(30) NOT NULL,
  `DIR_VEN` varchar(100) DEFAULT NULL,
  `USUA_VEN` varchar(25) NOT NULL,
  `CUI_VEN` varchar(13) NOT NULL,
  `TEL_VEN` varchar(9) DEFAULT NULL,
  `SEX_VEN` varchar(9) DEFAULT NULL,
  `FEC_ING` date NOT NULL,
  `FEC_NAC` date DEFAULT NULL,
  `EMA_VEN` varchar(100) DEFAULT NULL,
  `VENTAS` double(10,2) DEFAULT NULL,
  `LABORA` int(1) DEFAULT 1,
  `SUELDO` double(10,2) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `vendedores`
--

INSERT INTO `vendedores` (`ID_VEN`, `NOM_VEN`, `APE_VEN`, `DIR_VEN`, `USUA_VEN`, `CUI_VEN`, `TEL_VEN`, `SEX_VEN`, `FEC_ING`, `FEC_NAC`, `EMA_VEN`, `VENTAS`, `LABORA`, `SUELDO`) VALUES
('VE0001', 'ANDRES ROBERTO', 'MOLINA FAREZ', 'AV. LAS PALMERAS', 'robertoAnd', '0702365988', '072946565', 'MASCULINO', '2011-05-15', '1989-04-10', 'andres@hotmail.com', 0.00, 1, 350.50),
('VE0002', 'MARIA LUISA', 'SARANGO LOPEZ', 'PASAJE', 'maria_luisk', '0701454784', '072945444', 'FEMENINO', '2011-05-20', '1987-11-15', 'marialuisa@hotmail.com', 0.00, 1, 289.25),
('VE0003', 'DARIO JAVIER', 'QUEZADA SAMANIEGO', 'SANTA ROSA', 'jav_Dar', '0701245455', '072965985', 'MASCULINO', '2011-06-24', '1994-01-05', 'dariojavier2011@hotmail.com', 0.00, 1, 450.25),
('VE0004', 'ANA KARINA', 'JARAMILLO PEREZ', 'ARENILLAS', 'anaKar94', '0709898988', '072985985', 'FEMENINO', '2011-06-18', '1996-07-26', 'anitak@hotmail.com', 3417.62, 1, 400.20),
('VE0005', 'LUIS ANGEL', 'RUILOVA GALLARDO', 'HUAQUILLAS', 'an_Lois5', '0702230001', '072944699', 'MASCULINO', '2011-07-15', '1997-09-02', 'luis_angel@hotmail.com', 0.00, 1, 350.00),
('VE0006', 'Luisa Agustina', 'Sanchez Pascal', 'una direccion desconocidaaa', 'agustLuisa55', '3665448887979', '165456616', 'MASCULINO', '2013-01-16', '2000-01-16', 'cliente@tes.com', 20000.00, 0, 2555.00),
('VE0000', 'Celio', 'Portres', 'desconcoid', 'admin', '12345', '465649878', 'MASCULINO', '2011-02-03', '2000-05-05', 'andrespsotres@gmail.com', 20000.00, 0, 2555.00),
('VE0007', 'Andres', 'Quezada', '7a. Calle', 'admin', '3333333', '452454', 'Masculino', '2021-06-02', '2002-12-24', 'ancabrera1224@gmail.com', 0.00, 1, 2500.00);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `articulos`
--
ALTER TABLE `articulos`
  ADD PRIMARY KEY (`ID_ART`);

--
-- Indices de la tabla `clientes`
--
ALTER TABLE `clientes`
  ADD PRIMARY KEY (`ID_CLI`);

--
-- Indices de la tabla `facv_cab`
--
ALTER TABLE `facv_cab`
  ADD PRIMARY KEY (`NUM_FACV`);

--
-- Indices de la tabla `facv_det`
--
ALTER TABLE `facv_det`
  ADD PRIMARY KEY (`NUM_DET`);

--
-- Indices de la tabla `vendedores`
--
ALTER TABLE `vendedores`
  ADD PRIMARY KEY (`ID_VEN`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `facv_det`
--
ALTER TABLE `facv_det`
  MODIFY `NUM_DET` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=30;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
