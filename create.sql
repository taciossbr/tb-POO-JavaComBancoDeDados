BEGIN TRANSACTION;
CREATE TABLE IF NOT EXISTS `Pessoa_Interpreta_Musica` (
	`codigo_pessoa`	INTEGER NOT NULL,
	`codigo_musica`	INTEGER NOT NULL,
	FOREIGN KEY(`codigo_musica`) REFERENCES `Musica`(`codigo_musica`),
	FOREIGN KEY(`codigo_pessoa`) REFERENCES `Pessoa`(`codigo_pessoa`),
	PRIMARY KEY(`codigo_pessoa`,`codigo_musica`)
);
CREATE TABLE IF NOT EXISTS `Pessoa_Compoe_Musica` (
	`codigo_pessoa`	INTEGER NOT NULL,
	`codigo_musica`	INTEGER NOT NULL,
	FOREIGN KEY(`codigo_pessoa`) REFERENCES `Pessoa`(`codigo_pessoa`),
	PRIMARY KEY(`codigo_pessoa`,`codigo_musica`),
	FOREIGN KEY(`codigo_musica`) REFERENCES `Musica`(`codigo_musica`)
);
CREATE TABLE IF NOT EXISTS `Pessoa` (
	`codigo_pessoa`	INTEGER PRIMARY KEY AUTOINCREMENT,
	`nome_pessoa`	TEXT NOT NULL
);
CREATE TABLE IF NOT EXISTS `Musicas_CD` (
	`codigo_cd`	INTEGER NOT NULL,
	`codigo_musica`	INTEGER NOT NULL,
	FOREIGN KEY(`codigo_cd`) REFERENCES `CD`(`codigo_cd`),
	FOREIGN KEY(`codigo_musica`) REFERENCES `Musica`(`codigo_musica`),
	PRIMARY KEY(`codigo_cd`,`codigo_musica`)
);
CREATE TABLE IF NOT EXISTS `Musica` (
	`codigo_musica`	INTEGER PRIMARY KEY AUTOINCREMENT,
	`titulo_musica`	TEXT NOT NULL,
	`duracao`	INTEGER NOT NULL
);
CREATE TABLE IF NOT EXISTS `CD_internacional` (
	`codigo_cd`	INTEGER NOT NULL UNIQUE,
	`titulo_original_cd`	TEXT NOT NULL,
	`regiao`	INTEGER NOT NULL,
	PRIMARY KEY(`codigo_cd`)
);
CREATE TABLE IF NOT EXISTS `CD` (
	`codigo_cd`	INTEGER PRIMARY KEY AUTOINCREMENT,
	`titulo_cd`	TEXT NOT NULL
);
COMMIT;
