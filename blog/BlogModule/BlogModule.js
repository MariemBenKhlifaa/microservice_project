var mongoose = require("mongoose");
var Schema = mongoose.Schema;
var Blog = new Schema({
  author: {
    type: String,
  },
  title: {
    type: String,
  },
  content: {
    type: String,
  },
  date: {
    type: Date,
    default: Date.now,
  },
  img: {
    type: String,
  },
});

module.exports = mongoose.model("blog", Blog);
