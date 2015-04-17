from tastypie.resources import ModelResource
from apiengine.models import MessageModel
from tastypie.serializers import Serializer
from push_notifications.models import GCMDevice
from tastypie.authorization import Authorization


class MessageModelResource(ModelResource):
    class Meta:
        queryset = MessageModel.objects.all()
        resource_name = 'message'
        serializer = Serializer(formats=['json'])


class DeviceResource(ModelResource):
    class Meta:
        queryset = GCMDevice.objects.all()
        resource_name = 'gcm'
        serializer = Serializer(formats=['json'])
        authorization = Authorization()

