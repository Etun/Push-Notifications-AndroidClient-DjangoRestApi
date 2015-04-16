from django.db import models


class MessageModel(models.Model):
    title = models.CharField(max_length=125)
    text = models.TextField()
    date = models.DateTimeField('date', auto_now=False, auto_now_add=True)

    def __unicode__(self):
        return self.title

    class Meta:
        ordering = ["-date"]
