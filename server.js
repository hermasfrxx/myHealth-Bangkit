const express =  require("express");
const cors = require("cors");
const cookieSession = require("cookie-session");
const db = require("./apps/models");
require('./apps/routes/auth.routes')(app);
require('./apps/routes/user.routes')(app);

const app =  express();

app.use(cors());


const Role = db.role;

db.sequelize.sync({force: true}).then(() => {
    console.log('Drop and Resync Db');
    initial();
});

app.use(express.json());
app.use(express.urlencoded({extended:true}));

app.use(
    cookieSession({
        name: "myhealthapp-session",
        keys: ["COOKIE_SECRET"],
        httpOnly:true,
    })
);

app.get("/", (req, res) => {
    res.json({message: 'Welcome to myHealt API'});

});
const PORT = process.env.PORT || 8000;
app.listen(PORT, () => {
    console.log('Server is Running on PRT ${PORT}. ');
});

function initial() {
    Role.create({
      id: 1,
      name: "user"
    });
   
    Role.create({
      id: 2,
      name: "moderator"
    });
   
    Role.create({
      id: 3,
      name: "admin"
    });
  }
