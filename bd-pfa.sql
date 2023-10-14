-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : mer. 10 mai 2023 à 10:11
-- Version du serveur : 10.4.27-MariaDB
-- Version de PHP : 8.0.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `bd-pfa`
--

-- --------------------------------------------------------

--
-- Structure de la table `produit`
--

CREATE TABLE `produit` (
  `refProduit` int(11) NOT NULL,
  `intitule` text NOT NULL,
  `prixUn` int(11) NOT NULL,
  `tauxRemise` int(11) NOT NULL,
  `qtedispo` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `produit`
--

INSERT INTO `produit` (`refProduit`, `intitule`, `prixUn`, `tauxRemise`, `qtedispo`) VALUES
(1, 'aaa', 120, 12, 40),
(2, 'bbb', 20, 10, 150),
(3, 'ecran', 50, 20, 50),
(4, 'souris', 30, 10, 110);

-- --------------------------------------------------------

--
-- Structure de la table `vente`
--

CREATE TABLE `vente` (
  `numVente` int(11) NOT NULL,
  `dateVente` date NOT NULL,
  `montantVente` int(11) NOT NULL,
  `Qtevendu` int(11) NOT NULL,
  `ref_prod` int(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `vente`
--

INSERT INTO `vente` (`numVente`, `dateVente`, `montantVente`, `Qtevendu`, `ref_prod`) VALUES
(1, '2023-05-09', 500, 10, 3),
(2, '2023-05-09', 4500, 90, 3),
(3, '2023-05-09', 1200, 10, 1),
(4, '2023-05-09', 2400, 20, 1),
(5, '2023-05-09', 2400, 20, 1),
(6, '2023-05-09', 1200, 10, 1),
(7, '2023-05-09', 2400, 20, 1),
(8, '2023-05-09', 4800, 40, 1),
(9, '2023-05-09', 6000, 50, 1),
(10, '2023-05-09', 6000, 50, 1),
(11, '2023-05-09', 1200, 10, 1);

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `produit`
--
ALTER TABLE `produit`
  ADD PRIMARY KEY (`refProduit`);

--
-- Index pour la table `vente`
--
ALTER TABLE `vente`
  ADD PRIMARY KEY (`numVente`),
  ADD KEY `ref_prod` (`ref_prod`),
  ADD KEY `ref_prod_2` (`ref_prod`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `vente`
--
ALTER TABLE `vente`
  MODIFY `numVente` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `vente`
--
ALTER TABLE `vente`
  ADD CONSTRAINT `fk_vente_produit` FOREIGN KEY (`ref_prod`) REFERENCES `produit` (`refProduit`) ON DELETE CASCADE,
  ADD CONSTRAINT `ref_prod` FOREIGN KEY (`ref_prod`) REFERENCES `produit` (`refProduit`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
