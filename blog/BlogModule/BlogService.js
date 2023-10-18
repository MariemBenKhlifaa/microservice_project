var express = require('express');
var Blog = require ("./BlogModule");

async function addC(req, res, next) {

    try {
      const newBlog = new Blog({
        author: req.body.author,
        content: req.body.content,
        img:req.body.img.substring(req.body.img.lastIndexOf("\\") + 1)



      });

      const data = await newBlog.save();
      console.log(data);
      res.json(data);
    } catch(err) {
      res.status(500).json(err);
    }
  } 
  
  async function listC(req, res, next) {
    try {
      const obj = await Blog.find();
      console.log(obj);
      res.json(obj);
    } catch (error) {
      console.error(error);
      res.status(500).json({ error: 'Internal server error' });
    }
  }
  deleteC = async(req,res,next)=>{
    try {
        await Blog.findByIdAndDelete(req.params.id)
        res.status(200).json("Blog supprimer !")
    } catch (error) {
        res.json(error)
    }
}

    
    
module.exports = { addC,listC,deleteC};