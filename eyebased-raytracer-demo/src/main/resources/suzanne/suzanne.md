# Suzanne

Geometry for triangle mesh.

## Vertex Buffer

`suzanne.vb.deflate` - big-endian, deflated:

* vertex positions (x, y, z):
  * format: equivalent to [`VK_FORMAT_R32G32B32_SFLOAT`](https://www.khronos.org/registry/vulkan/specs/1.2/html/chap34.html#formats-definition)
  * offset: 0 bytes
  * stride: 16 bytes
* vertex normals (x, y, z):
  * format: equivalent to [`VK_FORMAT_A2B10G10R10_SNORM_PACK32`](https://www.khronos.org/registry/vulkan/specs/1.2/html/chap34.html#formats-definition)
  * offset: 12 bytes
  * stride: 16 bytes

## Index Buffer

`suzanne.ib.deflate` - big-endian, deflated:

* format: 16-bit unsigned integers
* count: 11 808 (3936 triangles)

