require("dotenv").config();
var http = require("http");
var createError = require("http-errors");
var express = require("express");
var path = require("path");
var cookieParser = require("cookie-parser");
var logger = require("morgan");
var indexRouter = require("./routes/index");
var blogsRouter = require("./BlogModule/BlogController");
var fileUploadRouter = require("./routes/fileUploadRoute");

const cors = require("cors");

var app = express();

app.use(cors({ origin: "http://localhost:4200" }));

app.use(cookieParser());
app.use(express.json());
var mongoose = require("mongoose");
var mongoconfig = require("./config/mongoConfig.json");
mongoose
  .connect(mongoconfig.uri, {
    useNewUrlParser: true,
    useUnifiedTopology: true,
  })
  .then(() => {
    console.log("db connected");
  })
  .catch((err) => {
    console.log(err);
  });
var server = http.createServer(app);

// view engine setup
app.set("views", path.join(__dirname, "views"));

app.set("view engine", "twig");

app.use(logger("dev"));
app.use(express.urlencoded({ extended: true }));

app.use(express.static(path.join(__dirname, "public")));

app.use("/", indexRouter);
app.use("/upload", fileUploadRouter);
app.use("/blogs", blogsRouter);
app.post("/blogs/addc", (req, res) => {
  res.setHeader("Access-Control-Allow-Origin", "http://localhost:4200");
  res.setHeader("Access-Control-Allow-Methods", "GET, POST, OPTIONS, PUT, DELETE");
  res.setHeader("Access-Control-Allow-Headers", "Content-Type");
  res.status(200).send("Blog added successfully");
});
app.post("/upload", (req, res) => {
  res.setHeader("Access-Control-Allow-Origin", "http://localhost:4200");
  res.setHeader("Access-Control-Allow-Methods", "GET, POST, OPTIONS, PUT, DELETE");
  res.setHeader("Access-Control-Allow-Headers", "Content-Type");
  res.status(200).send("Blog added successfully");
});

// catch 404 and forward to error handler
app.use(function (req, res, next) {
  next(createError(404));
});

// error handler
app.use(function (err, req, res, next) {
  // set locals, only providing error in development
  res.locals.message = err.message;
  res.locals.error = req.app.get("env") === "development" ? err : {};

  // render the error page
  res.status(err.status || 500);
  res.render("error");
});

module.exports = app;
