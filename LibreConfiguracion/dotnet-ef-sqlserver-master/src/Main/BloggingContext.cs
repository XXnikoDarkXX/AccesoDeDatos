using Microsoft.EntityFrameworkCore;

namespace Main
{
    public class BloggingContext
        : DbContext
    {
        private const string ConnectionString = "Server=localhost;Database=Sample;user=sa;password=Password12!";
        public DbSet<Blog> Blogs { get; set; }
        public DbSet<Post> Posts { get; set; }

        protected override void OnConfiguring(DbContextOptionsBuilder options)
            => options.UseSqlServer(ConnectionString);
    }
}