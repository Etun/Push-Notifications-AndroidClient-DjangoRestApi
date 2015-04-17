from django.db import models
from push_notifications.models import APNSDevice, GCMDevice


class MessageModel(models.Model):
    title = models.CharField(max_length=125)
    text = models.TextField()
    date = models.DateTimeField('date', auto_now=False, auto_now_add=True)

    def __unicode__(self):
        return self.title

    def save(self, *args, **kwargs):
        device = GCMDevice.objects.all()
        device.send_message(None, extra={"title": self.title, "text": self.text, "date": str(self.date)})
        return super(MessageModel, self).save(*args, **kwargs)

    class Meta:
        ordering = ["-date"]