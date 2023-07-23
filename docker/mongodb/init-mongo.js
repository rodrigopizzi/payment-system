db = db.getSiblingDB("money-transfer");

db.createUser(
    {
        user: "moneyTransfer",
        pwd: "123456",
        roles: [ { role: "readWrite", db: "test" },
            { role: "read", db: "reporting" } ]
    }
);