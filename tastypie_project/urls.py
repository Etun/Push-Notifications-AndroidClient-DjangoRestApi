from django.conf.urls import patterns, include, url
from django.contrib import admin
from apiengine.api import MessageModelResource, DeviceResource

resource_model = MessageModelResource()
device_resource = DeviceResource()


urlpatterns = patterns('',

    url(r'^admin/', include(admin.site.urls)),
    url(r'^api/', include(resource_model.urls)),
    url(r'^device/', include(device_resource.urls)),
)
