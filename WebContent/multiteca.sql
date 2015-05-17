-- phpMyAdmin SQL Dump
-- version 3.4.10.1deb1
-- http://www.phpmyadmin.net
--
-- Servidor: localhost
-- Tiempo de generación: 14-05-2015 a las 08:46:21
-- Versión del servidor: 5.5.41
-- Versión de PHP: 5.3.10-1ubuntu3.17

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de datos: `multiteca`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ARTISTA`
--

CREATE TABLE IF NOT EXISTS `ARTISTA` (
  `ID_ARTISTA` int(11) NOT NULL AUTO_INCREMENT,
  `TIPO` varchar(50) DEFAULT NULL,
  `AÑONACIMIENTO` int(11) DEFAULT NULL,
  `FOTO` blob,
  PRIMARY KEY (`ID_ARTISTA`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `DISCO`
--

CREATE TABLE IF NOT EXISTS `DISCO` (
  `ISMN` int(11) NOT NULL DEFAULT '0',
  `TITULO` varchar(50) NOT NULL,
  `AUTOR` varchar(30) NOT NULL,
  `AÑOEDICION` int(11) DEFAULT NULL,
  `DISCOGRAFICA` varchar(50) DEFAULT NULL,
  `NUMEROCANCIONES` int(11) DEFAULT NULL,
  PRIMARY KEY (`ISMN`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `LIBRO`
--

CREATE TABLE IF NOT EXISTS `LIBRO` (
  `ISBN` int(11) NOT NULL,
  `TITULO` varchar(50) DEFAULT NULL,
  `AUTOR` varchar(30) DEFAULT NULL,
  `AÑOEDICION` int(11) DEFAULT NULL,
  `EDITORIAL` varchar(50) DEFAULT NULL,
  `NUMEROPAGINAS` int(11) DEFAULT NULL,
  PRIMARY KEY (`ISBN`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `LIBRO`
--

INSERT INTO `LIBRO` (`ISBN`, `TITULO`, `AUTOR`, `AÑOEDICION`, `EDITORIAL`, `NUMEROPAGINAS`) VALUES
(14257, 'asddas', 'asdasd', 1589, 'dasasd', 254);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `PELICULA`
--

CREATE TABLE IF NOT EXISTS `PELICULA` (
  `ISAN` int(11) NOT NULL,
  `TITULO` varchar(50) DEFAULT NULL,
  `AUTOR` varchar(30) DEFAULT NULL,
  `AÑOEDICION` int(11) DEFAULT NULL,
  `PRODUCTORA` varchar(50) DEFAULT NULL,
  `INTERPRETES` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`ISAN`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `PELICULA`
--

INSERT INTO `PELICULA` (`ISAN`, `TITULO`, `AUTOR`, `AÑOEDICION`, `PRODUCTORA`, `INTERPRETES`) VALUES
(1456, 'dsadas', 'asddas', 1987, 'asdasd', 'asdsdaasd');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
