provider "docker" {
  # Windows
  host = "npipe:////.//pipe//docker_engine"
}

resource "docker_container" "demo-xss" {
  name  = "demo-xss"
  image = "${docker_image.demo-xss.latest}"
#   image = "dockerworkshopsydor/demo-xss:latest"

  ports {
    internal = 8080
    external = 8080
  }
}

resource "docker_image" "demo-xss" {
  name = "dockerworkshopsydor/demo-xss:latest"
}
