using Microsoft.Owin;
using Owin;

[assembly: OwinStartupAttribute(typeof(MS2.Web.Startup))]
namespace MS2.Web
{
    public partial class Startup
    {
        public void Configuration(IAppBuilder app)
        {
            ConfigureAuth(app);
        }
    }
}
