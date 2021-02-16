using System;
using System.Linq;
using Microsoft.EntityFrameworkCore;

namespace Main
{
    class Program
    {
        static void Main(string[] args)
        {
            WriteData();
            ReadData();
        }

        private static void WriteData()
        {
            var blogOne =
                new Blog
                {
                    Url = "https://blog-one.com"
                };

            var blogTwo =
                new Blog
                {
                    Url = "https://blog-two.com"
                };

            var postOne =
                new Post
                {
                    Title = "Post One",
                    Content = "This is the post one",
                    Blog = blogTwo
                };

            var postTwo =
                new Post
                {
                    Title = "Post Two",
                    Content = "This is the post two",
                    Blog = blogTwo
                };

            var postThree =
                new Post
                {
                    Title = "Post Three",
                    Content = "This is the post three",
                    Blog = blogTwo
                };

            using var bloggingWriteContext = new BloggingContext();
            bloggingWriteContext.Blogs.AddRange(blogOne, blogTwo);
            bloggingWriteContext.Posts.AddRange(postOne, postTwo, postThree);
            bloggingWriteContext.SaveChanges();
        }

        private static void ReadData()
        {
            using var bloggingReadContext = new BloggingContext();
            var blogs = 
                bloggingReadContext
                    .Blogs
                    .Include(x => x.Posts)
                    .Where(blog => blog.Posts.Any(post => post.Title.Contains("whatever")))
                    .ToList();

            foreach (var blog in blogs)
            {
                Console.WriteLine($"{blog.BlogId}-{blog.Url}");
                var posts = blog.Posts;
                foreach (var post in posts)
                {
                    Console.WriteLine($"{blog.BlogId}.{post.PostId}-{post.Title}");
                }
            }
        }
    }
}