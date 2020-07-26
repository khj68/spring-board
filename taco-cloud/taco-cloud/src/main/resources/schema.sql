create table if not exists Ingredient (
    id varchar(4) not null,
    name varchar(25) not null,
    type varchar(10) not null
);

create table IF NOT EXISTS Taco (
    id identity,
    name VARCHAR(50) not null,
    createdAt TIMESTAMP not null
);

create table IF NOT EXISTS Taco_Ingredients (
    taco BIGINT NOT NULL,
    ingredient VARCHAR(4) NOT NULL
);

alter table Taco_Ingredients
    add foreign key (taco) references Taco(id);
alter TABLE Taco_Ingredients
    add foreign key (ingredient) references Ingredient(id);

create TABLE IF NOT EXISTS Taco_Order (
    id identity,
    deliveryName VARCHAR(50) not null,
    deliveryStreet VARCHAR(50) not null,
    deliveryCity VARCHAR(50) not null,
    deliveryState VARCHAR(2) not null,
    deliveryZip VARCHAR(10) not null,
    ccNumber VARCHAR(16) not null,
    ccExpiration VARCHAR(5) not null,
    ccCVV VARCHAR(3) not null,
    placedAt TIMESTAMP not null
);

create TABLE IF NOT EXISTS Taco_Order_Tacos (
    tacoOrder BIGINT NOT NULL,
    taco BIGINT not null
);

alter TABLE Taco_Order_Tacos
    add foreign key (tacoOrder) references Taco_Order(id);
alter TABLE Taco_Order_Tacos
    add foreign key (taco) references Taco(id);
