using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

using System.IO;

namespace MovieChecker_Desktop.Classes
{
    public static class Loader
    {
        public static string LoadTextFile(string path)
        {
            using (var fileStream = File.OpenRead(path))
            {
                StreamReader streamReader = new StreamReader(fileStream);
                string text = streamReader.ReadToEnd();

                return text;
            }
        }
    }
}
