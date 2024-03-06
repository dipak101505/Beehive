    Api:

    GET http://localhost:9091/getInfo
    Response (example):

    [
      {
        "id": 1,
        "name": "Beehive Organization",
        "contact": "BHV123",
        "address": "123 Main Street, Anytown, CA",
        "content": "We provide resources for the community.",
        "image_url": "https://example.com/beehive_logo.png"
      },
      {
        "id": 2,
        "name": "Community Center",
        "contact": "CC100",
        "address": "456 Elm Street, Anytown, CA",
        "content": "Offers various programs and activities.",
        "image_url": null
      },
      {
        "id": 3,
        "name": "vortex Organization",
        "contact": "BHV123",
        "address": "123 Main Street, Anytown, CA",
        "content": "We provide alcohol for the community.",
        "image_url": "https://example.com/beehive_logo.png"
      },
      {
        "id": 4,
        "name": "plugin Center",
        "contact": "CC100",
        "address": "456 Elm Street, Anytown, CA",
        "content": "Offers various programs and activities.",
        "image_url": null
      }

    ]

    GET https://localhost:9091/getInfo?search=community alcohol
    Response (example, search term matches "Community", "alcohol"):

    [
      {
        "id": 2,
        "name": "Community Center",
        "contact": "CC100",
        "address": "456 Elm Street, Anytown, CA",
        "content": "Offers various programs and activities.",
        "image_url": null
      },
      {
        "id": 3,
        "name": "vortex Organization",
        "contact": "BHV123",
        "address": "123 Main Street, Anytown, CA",
        "content": "We provide alcohol for the community.",
        "image_url": "https://example.com/beehive_logo.png"
      }
    ]

    POST https://localhost:9091/addInformation

    Content-Type: multipart/form-data

    --- (boundary)
    Content-Disposition: form-data; name="file"; filename="my_image.jpg"
    Content-Type: image/jpeg

    (Image data)

    --- (boundary)
    Content-Disposition: form-data; name="infoData"

    {
      "name": "Beehive Event",
      "contact": "BHEVENT01",
      "address": "123 Main Street, Anytown, CA",
      "content": "Join our upcoming event!"
    }

    --- (boundary)--
    Response:
    {
      "id": 3,
      "name": "Beehive Event",
      "contact": "BHEVENT01",
      "address": "123 Main Street, Anytown, CA",
      "content": "Join our upcoming event!",
      "image_url": "https://[your_server_address]/images/my_image.jpg"  // Assuming image is stored at this URL
    }

    request:
    POST https://localhost:9091/addInformation

    Content-Type: application/json

    {
      "name": "Library",
      "contact": "LIB123",
      "address": "456 Elm Street, Anytown, CA",
      "content": "Offers a wide variety of books and resources."
    }

    Response:
    {
      "id": 4,
      "name": "Library",
      "contact": "LIB123",
      "address": "456 Elm Street, Anytown, CA",
      "content": "Offers a wide variety of books and resources.",
      "image_url": null
    }

